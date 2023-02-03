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

      body: JSON.stringify(object),
      })
        .then((response: Response) => {
              console.log(response)
        if(response.status === 200){
            console.log("Logged in successfully")
            return response;
        }
        else if(response.status === 401){
            console.log("Wrong credentials")
        }
        else if(response == null){
            console.log("Error");
        }else{
            doThrow(
                new Error(
                    "Status was: " + response.statusText + " " + response.status + " " + params
                )
            )
        }

        });
      },
      postData(object: any) : Promise<any> {


          const postQueueEndpoint = "/any/login";
          return ApiPost.apiCall(postQueueEndpoint, object).then((data) => data);
      },

        createData(object: any) : Promise<any> {


        const postQueueEndpoint = "/any/register";
        return ApiPost.apiCall(postQueueEndpoint, object).then((data) => data);
        },

  };

export default ApiPost;