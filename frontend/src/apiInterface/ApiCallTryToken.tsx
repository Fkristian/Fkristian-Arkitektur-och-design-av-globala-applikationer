function doThrow(e: Error) {
    throw e;
}

const url = "http://localhost:8088";

interface Params {
    [key: string]: any;
}

const ApiCall = {
    apiCall(params: String) {
        return fetch(url + params, {
            method: "GET", // HTTP method
            //crossDomain: true,
            headers: {
                // HTTP headers
                "Content-Type": "application/json",
                "Access-Control-Request-Headers": "Origin, X-Requested-With, Content-Type, Accept",
                "Authorization":"Bearer "+ localStorage.getItem("access_token"),
            },
        })
            .then((response: Response) =>
                response.status === 200
                    ? response
                    : doThrow(
                        new Error(
                            "Status was: " + response.statusText + " " + response.status + " " + params
                        )
                    )
            )
            .then((response) => {
                if(response == null){
                    console.log("Error");
                }else{
                    return response.json();
                }

            });
    },
    admin() {
        console.log(localStorage.getItem("access_token"))
        const getQueueEndpoint = "/admin";
        return ApiCall.apiCall(getQueueEndpoint).then((data) => data);
    },
    getAllApplicants() {
        const getQueueEndpoint = "/api/application/all";
        return ApiCall.apiCall(getQueueEndpoint).then((data) => data);
    },
}
export default ApiCall;