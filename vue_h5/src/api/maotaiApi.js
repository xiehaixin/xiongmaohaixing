import request from '@/utils/request'

// 获取茅台验证码
export function getSlideCode() {
  return request({
    url: '/api/maotaiApi/register/getSlideCode',
    method: 'post',
  })
}