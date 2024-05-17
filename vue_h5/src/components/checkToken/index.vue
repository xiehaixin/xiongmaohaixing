<template>
    <div class="privacy_div">
        <div class="p_title">切换 tokens</div>
        <div class="inputDiv" v-for="(inputDiv, index) in listDiv" :key="index">
            <p @click="inputDiv.model=''">{{inputDiv.name}}：</p>
            <div class="inputs">
                <div>
                    <input type="tel" v-model.number='inputDiv.model' placeholder="账号">
                </div>
                <div>
                    <textarea v-model="inputDiv.token" placeholder="token"></textarea>
                </div>
            </div>
            <button @click='checkToken(inputDiv)'>切换</button>
        </div>
    </div>
</template>
<script>
import { JJLinCheckToken } from '@/api/privacyApi'
export default {
    data() {
        return {
            listDiv: [
                {
                    name: 'JJLin',
                    model: '',
                    token: ''
                },
                {
                    name: '看漫APP',
                    model: '',
                    token: ''
                },
                {
                    name: 'i茅台APP',
                    model: '',
                    token: ''
                },
            ],
            result: '',
            cache: ''
        }
    },
    methods: {
        checkToken(inputDiv){
            let method;
            if(inputDiv.name === 'JJLin'){
                method = JJLinCheckToken;
            }
            if(inputDiv.model === this.cache){
                return
            }
            this.cache = inputDiv.model

            let params = {
                account: inputDiv.model,
                authorization: inputDiv.token
            }
            method(params).then(res =>{
                if(!res || res.length == 0){
                    return
                }
                alert('成功')
            })
        }
    }
}
</script>

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
    .inputs {
        flex: 1;
        height: 50%;
        div {
            display: flex;
            align-items: center;
        }
        input {
            flex: 1;
            height: 50%;
        }
        textarea{
            flex: 1;
            height: 50%;
        }
    }
    button {
        width: 70px;
        margin-left: 20px;
    }
}
</style>