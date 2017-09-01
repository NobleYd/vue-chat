import axios from 'axios'

var base = 'http://localhost:80'

export const loginApi = params => { return axios.get(`${base}/login`, {params: params}) }
