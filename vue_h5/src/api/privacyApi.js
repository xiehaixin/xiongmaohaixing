import request from '@/utils/request'

// qq
export function qq(qq) {
  return request({
    url: '/privacy/qq/'+qq,
    method: 'GET',
  })
}

// phone
export function phone(phone) {
  return request({
    url: '/privacy/phone/'+phone,
    method: 'GET',
  })
}
// wb
export function wb(uid) {
  return request({
    url: '/privacy/wb/'+uid,
    method: 'GET',
  })
}
// JJLin切换token
export function JJLinCheckToken(_params) {
  return request({
    url: '/privacy/sign/jj20/addToken',
    method: 'POST',
    data: _params
  })
}
