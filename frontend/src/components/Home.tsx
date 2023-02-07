import Reract from "react";
import {
    Button, Text
} from "@chakra-ui/react"
import { useNavigate } from "react-router-dom";

export default function LogIn() {
    const navigate = useNavigate();
    function newSide(){
        navigate("/home");
    }
    function goToForm(){
        navigate("/applicantForm");
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
             </div>
};