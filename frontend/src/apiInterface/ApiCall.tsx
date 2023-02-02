function doThrow(e: Error) {
    throw e;
}

const url = "http://localhost:8081";

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
                "Access-Control-Allow-Headers":
                    "Origin, X-Requested-With, Content-Type, Accept",
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
                    return response.json() as Promise<any>;
                }

            });
    },
    getData() {
        const getQueueEndpoint = "/api/users";
        return ApiCall.apiCall(getQueueEndpoint).then((data) => data);
    },

}

export default ApiCall;