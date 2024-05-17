import request from '@/utils/request'

// homeClick
export function homeClick(x, y, w, h) {
    return request({
      url: '/homeClick?x=' + x + '&y=' + y + '&w=' + w + '&h=' + h,
      method: 'GET',
    })
  }