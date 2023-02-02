import Reract, {useState} from "react";
import {
    Input,
    Flex,
    Button
} from "@chakra-ui/react"
import { useNavigate } from "react-router-dom";
import ApiCall from "../apiInterface/ApiCall";

export default function LogIn() {

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


    function logInAttempt() {
        console.log(formData)
        navigate("/home");
    }

    function test(){
        ApiCall.getData().then(response => console.log(response));
    }

    return(
    <Flex>

        <form>
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
            <Button
                variant="link"
                width="100%"
                colorScheme="blue"
                onClick={test}
            >
                {" "}
                Test
            </Button>
        </form>
    </Flex>
    )

};