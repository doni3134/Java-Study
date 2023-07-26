
const serverHost = "http://localhost:8080";

async function getTokens(mid, mpw) {

    const response = await axios.post(`${serverHost}/generateToken`, {mid,mpw})

    const data = response.data

    //console.log(data)

    localStorage.setItem("access", data.access)
    localStorage.setItem("refresh", data.refresh)

    return data
}

async function getServerData(path, data){

    const accessToken = localStorage.getItem("access")

    console.log("accessToken", accessToken)

    if(!accessToken){
        throw {ERROR:'Access Token is null'}
    }

    const authHeader = {"Authorization": `Bearer ${accessToken}`}

    try{
        const response = await axios.get(`${serverHost}${path}`,
            {params: data, headers:authHeader})

        return response.data
    }catch(err){

        if(err.response.status === 401){
            console.log("needs to refresh access token")
            try {
                await refreshJWT(err.response.data['Refresh URL'])
                //갱신된 토큰을 이용해서 다시 호출
                getServerData(path,data)
            }catch(refreshErr){
                throw {ERROR:"RefreshFail"}
            }

        }else{
            throw err.response.data
        }
    }
}

async function refreshJWT(refreshURL){

    const refreshToken = localStorage.getItem("refresh")
    const urlEncodedHeader = {"Content-Type": `application/x-www-form-urlencoded`}

    const params = new URLSearchParams()
    params.append('grant_type', 'refresh_token')
    params.append('refresh_token', refreshToken)

    const response = await axios.post(`${serverHost}${refreshURL}`, params, {headers: urlEncodedHeader})

    console.log("새로운 Access Token 이 생성됨 ")
    console.log(response.data)
    localStorage.setItem("access", response.data.access)
    localStorage.setItem("refresh", response.data.refresh)
}