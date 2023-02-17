import Reract from "react";
import {
    Button, Text
} from "@chakra-ui/react"
import { useNavigate } from "react-router-dom";
import ApiCallTryToken from "../apiInterface/ApiCallTryToken";

export default function LogIn() {
    const navigate = useNavigate();
    function newSide(){
        navigate("/home");
    }
    function goToForm(){
        navigate("/applicantForm");
    }
    function test(){

        ApiCallTryToken.admin().then((response:any) => console.log(response))
    }


    function test2() {
        ApiCallTryToken.getAllApplicants().then((response:any) => console.log(response))
    }

    return <div>
                <Text>Welcome to home</Text>
        <Button
            width="100%"
            colorScheme="blue"
            onClick={goToForm}
            mb={3}
        >
            {" "}
            Apply
        </Button>
        <Button
            width="100%"
            colorScheme="blue"
            onClick={test}
            mb={3}
        >
            {" "}
            test
        </Button>
        <Button
            width="100%"
            colorScheme="blue"
            onClick={test2}
            mb={3}
        >
            {" "}
            test2
        </Button>
             </div>
};