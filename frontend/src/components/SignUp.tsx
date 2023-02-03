import Reract, {useState} from "react";
import {
    Input,
    Flex,
    Button
} from "@chakra-ui/react"
import { useNavigate } from "react-router-dom";
import ApiCall from "../apiInterface/ApiCall";
import ApiPost from "../apiInterface/ApiPost";

export default function SignUp() {

    const [formData, setFormData] = useState({
        firstname: "",
        lastname: "",
        emailaddress: "",
        personnumber: "",
        username: "",
        password: "",
    })

    const navigate = useNavigate();

    function handelChange(event: { target: { name: any; value: any; }; }) {
        const { name, value } = event.target;
        setFormData((prevValues) => {
            return {
                ...prevValues,
                [name]: value,
            };
        });
    }

    const handleResponse = (response : Response) => {
        if (response.ok) {
            navigate("/home");
        }
        console.log(response.status)
    };

    function registerAttempt() {
        const post = {
            username :  formData.username,
            password : formData.password

        }
        ApiPost.postData(post).then(response => handleResponse(response));
    }

    function goToLogin() {
        navigate("/");
    }

    return (
        <Flex>
            <form>
                <p>
                    <Input
                        className="LogInPage--form"
                        type="text"
                        placeholder="First name"
                        onChange={handelChange}
                        name="firstname"
                        mb={3}
                        value={formData.firstname}
                    />
                </p>
                <p>
                    <Input
                        className="LogInPage--form"
                        type="text"
                        placeholder="Last name"
                        onChange={handelChange}
                        name="lastname"
                        mb={3}
                        value={formData.lastname}
                    />
                </p>
                <p>
                    <Input
                        className="LogInPage--form"
                        type="text"
                        placeholder="Email address"
                        onChange={handelChange}
                        name="emailaddress"
                        mb={3}
                        value={formData.emailaddress}
                    />
                </p>
                <p>
                    <Input
                        className="LogInPage--form"
                        type="text"
                        placeholder="Person number"
                        onChange={handelChange}
                        name="personnumber"
                        mb={3}
                        value={formData.personnumber}
                    />
                </p>
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
                    onClick={registerAttempt}
                    mb={3}
                >
                    {" "}
                    Register
                </Button>
                <Button
                    variant="link"
                    width="100%"
                    colorScheme="blue"
                    onClick={goToLogin}
                >
                    {" "}
                    Log in
                </Button>
            </form>
        </Flex>
    )
};