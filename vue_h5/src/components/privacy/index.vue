<template>
    <div class="privacy_div">
        <div class="p_title">信息查询</div>
        <div class="inputDiv" v-for="(inputDiv, index) in listDiv" :key="index">
            <p @click="inputDiv.model=''">{{inputDiv.name}}：</p>
            <input type="tel" v-model.number='inputDiv.model'>
            <button @click='select(inputDiv)'>查询</button>
        </div>
        <div v-if="result">
            <div v-if="result.length > 0">
                <div v-for="(re, index) in result" :key="index">
                    <div class="result" v-if="re.qq">
                        <p class="content">qq：{{re.qq}}</p>
                        <p class="copyBtn" v-clipboard:copy="re.qq">复制</p>
                    </div>
                    <div class="result" v-if="re.phone">
                        <p class="content">phone：{{re.phone}}</p>
                        <p class="copyBtn" v-clipboard:copy="re.phone">复制</p>
                    </div>
                    <div class="result" v-if="re.email">
                        <p class="content">email：{{re.email}}</p>
                        <p class="copyBtn" v-clipboard:copy="re.email">复制</p>
                    </div>
                    <div class="result" v-if="re.idno">
                        <p class="content">idno：{{re.idno}}</p>
                        <p class="copyBtn" v-clipboard:copy="re.idno">复制</p>
                    </div>
                    <div class="result" v-if="re.name">
                        <p class="content">name：{{re.name}}</p>
                        <p class="copyBtn" v-clipboard:copy="re.name">复制</p>
                    </div>
                    <div class="result" v-if="re.wb">
                        <p class="content">wb：{{re.wb}}</p>
                        <div class="copyBtns">
                            <p class="copyBtn" v-clipboard:copy="re.wb">复制id</p>
                            <p class="copyBtn" v-clipboard:copy="'https://m.weibo.cn/u/'+re.wb">复制网址</p>
                        </div>
                    </div>
                    <div class="sfDiv" v-if="re.sf">
                        sf：
                        <div class="sf">
                            <div class="sfContent"><p class="content">name：{{re.sf.name}}</p><p class="copyBtn" v-clipboard:copy="re.sf.name">复制</p></div>
                            <div class="sfContent"><p class="content">phone：{{re.sf.phone}}</p><p class="copyBtn" v-clipboard:copy="re.sf.phone">复制</p></div>
                            <div class="sfContent"><p class="content">province：{{re.sf.province}}</p><p class="copyBtn" v-clipboard:copy="re.sf.province">复制</p></div>
                            <div class="sfContent"><p class="content">city：{{re.sf.city}}</p><p class="copyBtn" v-clipboard:copy="re.sf.city">复制</p></div>
                            <div class="sfContent"><p class="content">dist：{{re.sf.dist}}</p><p class="copyBtn" v-clipboard:copy="re.sf.dist">复制</p></div>
                            <div class="sfContent"><p class="content">addr：{{re.sf.addr}}</p><p class="copyBtn" v-clipboard:copy="re.sf.addr">复制</p></div>
                        </div>
                    </div>
                    <div class="result" v-if="re.wb">
                        <iframe :src="'https://m.weibo.cn/u/'+re.wb" frameborder="0" width="100%" height="400px"></iframe>
                    </div>
                </div>
            </div>
            <div v-else>
                未查询到数据
            </div>
        </div>
        
    </div>
</template>

<script>
import { qq, phone, wb } from '@/api/privacyApi'
export default {
    data() {
        return {
            listDiv: [
                {
                    name: 'qq',
                    model: ''
                },
                {
                    name: 'phone',
                    model: ''
                },
                {
                    name: 'wb',
                    model: ''
                },
            ],
            result: '',
            cache: ''
        }
    },
    methods: {
        select(inputDiv){
            let method;
            if(inputDiv.name === 'qq'){
                method = qq;
            }else if(inputDiv.name === 'wb'){
                method = wb;
            }else{
                method = phone;
            }
            if(inputDiv.model === this.cache){
                return
            }
            this.cache = inputDiv.model
            this.result = [];
            if(!inputDiv.model){
                return
            }
            let _this = this
            method(inputDiv.model).then(res => {
                if(!res || res.length == 0){
                    return
                }
                for (let i = 0; i < res.length; i++) {
                    let re = {};
                    if(res[i].qq){
                        re.qq = res[i].qq
                    }

                    if(res[i].phone){
                        re.phone = res[i].phone
                    }

                    if(res[i].uid){
                        re.wb = res[i].uid
                    }

                    if(res[i].idno){
                        re.idno = res[i].idno
                    }

                    if(res[i].email){
                        re.email = res[i].email
                    }

                    if(res[i].name){
                        re.name = res[i].name
                    }

                    if(res[i].addr){
                        re.sf = res[i]
                    }

                    _this.result.push(re)
                }

                // 手机号展示去重
                let len = _this.result.length
                for (let i = 0; i < len; i++) {
                    if( (i+1) < len && _this.result[i].phone ){
                        let tem = i + 1;
                        let _phone = _this.result[i].phone
                        while(tem < len){
                            if( _this.result[tem].phone && _this.result[tem].phone === _phone ){
                               _this.result[tem].phone = undefined 
                            }
                            tem++;
                        } 
                    }
                    
                }                
            })
        },
        onCopy(e){
            alert('copy success' + e.text)
        },
        onCopyError(e){
            alert(1)
        }
    },
    components: {

    }
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style lang="scss" scoped>
.privacy_div{
    padding: 20px;
}
.p_title {
    text-align: center;
    font-size: 20px;
    font-weight: 600;
    margin-bottom: 20px;
}
.inputDiv {
    display: flex;
    align-items: center;
    justify-content: space-between;
    height: 50px;
    p {
        min-width: 60px;
    }
    input {
        flex: 1;
        height: 50%;
    }
    button {
        width: 70px;
        margin-left: 20px;
    }
}
.result {
    margin: 10px 0;
    padding: 10px;
    display: flex;
    align-items: center;
    justify-content: space-between;
}
.sfDiv {
    padding: 10px;
    .sf{
        .sfContent{
            padding: 20px;
            padding-right: 0;
            display: flex;
            align-items: center;
            justify-content: space-between;
        }
    }
}
.copyBtns{
    display: flex;
    align-items: center;
    justify-content: space-between;
    .copyBtn {
        width: 64px;
        margin-left: 10px;
    }
}
.copyBtn{
    height: 30px;
    background: #999999;
    color: #FFFFFF;
    width: 50px;
    font-size: 14px;
    display: flex;
    align-items: center;
    justify-content: center;
    border-radius: 7px;
}
</style>