function doThrow(e: any) {
  throw e;
}


const url = "http://localhost:8088";

const ApiPost = {
  apiCall(params: string, object: any) : Promise<any> {
      return fetch(url + params, {
      method: "POST", // HTTP method
      //crossDomain: true,
      headers: {
          "Content-Type": "application/json",
          "Access-Control-Allow-Headers":
              "Origin, X-Requested-With, " +
              "Content-Type, Accept",
          },

      body: JSON.stringify({username: "MartinCummings", password: "test123"}),
      })
          .then((response: Response) =>
          response.status === 200
              ? response
              : doThrow(
                  new Error(
                  "Status was: " + response.statusText + " " + response.status
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
      postData(object: any) : Promise<any> {


          const postQueueEndpoint = "/any/login";
          return ApiPost.apiCall(postQueueEndpoint, object).then((data) => data);
      },

  };

export default ApiPost;