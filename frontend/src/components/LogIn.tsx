import Reract, {useState} from "react";
import {
    Input,
    Flex,
    Button, Text
} from "@chakra-ui/react"
import { useNavigate } from "react-router-dom";
import ApiCall from "../apiInterface/ApiCall";
import ApiPost from "../apiInterface/ApiPost";

export default function LogIn() {
    const [errorMessage, setErrorMessage] = useState("")
    const [formData, setFormData] = useState({
        username: "",
        password: "",
    })
    const navigate = useNavigate();

    function hold(){

    }
    function handelChange(event: { target: { name: any; value: any; }; }) {
        const { name, value } = event.target;
        setFormData((prevValues) => {
            return {
                ...prevValues,
                [name]: value,
            };
        });
    }
    function goToCreateAccount() {
        navigate("/SignUp");
    }

    const handleResponse = (response : Response) => {

        if (response.ok) {
            var token = null;
            response.json().then((token:any) => {
                window.localStorage.setItem('access_token', token.token)
                console.log(token.token)
            })

            navigate("/home");
        }else if (response.status === 401){
            setErrorMessage("Wrong credentials")
        }


    };

    function logInAttempt() {
        if(formData.password === "" || formData.username === ""){
            setErrorMessage("Fill in all fields")
        }else{
            const post = {
                username :  formData.username,
                password : formData.password

            }
            ApiPost.postData(post).then(response => handleResponse(response));
        }



    }

    function test(){
        ApiCall.getData().then(response => console.log(response));
    }


    return(
    <Flex>

        <form>
            <Text
                color='red'> {errorMessage} </Text>
            <p>
                <Input
                    className="LogInPage--form"
                    type="text"
                    placeholder="Username"
                    onChange={handelChange}
                    name="username"
                    mb={3}
                    value={formData.username}
                />
            </p>
            <p>
                <Input
                    className="LogInPage--form"
                    type="password"
                    placeholder="Password"
                    onChange={handelChange}
                    name="password"
                    mb={3}
                    value={formData.password}
                />
            </p>
            <Button
                width="100%"
                colorScheme="blue"
                onClick={logInAttempt}
                mb={3}
            >
                {" "}
                Log in
            </Button>
            <Button
                variant="link"
                width="100%"
                colorScheme="blue"
                onClick={goToCreateAccount}
            >
                {" "}
                Create an account
            </Button>
        </form>
    </Flex>
    )

};