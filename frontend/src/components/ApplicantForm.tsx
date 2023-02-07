import Reract, {useState} from "react";
import {
    Input,
    Flex,
    Button, Text, Select
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



    const handleResponse = (response : Response) => {
        if (response.ok) {
            navigate("/home");
        }else if (response.status === 401){
            setErrorMessage("Wrong credentials")
        }


    };



    function test(){
        ApiCall.getData().then(response => console.log(response));
    }


    return(
    <Flex>

        <form>
            <Text
                color='red'> {errorMessage} </Text>
            <Select placeholder='Select option'>
                <option value='option1'>Option 1</option>
                <option value='option2'>Option 2</option>
                <option value='option3'>Option 3</option>
            </Select>
        </form>
    </Flex>
    )

};