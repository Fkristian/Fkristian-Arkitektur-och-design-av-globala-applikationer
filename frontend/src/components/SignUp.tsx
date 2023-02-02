import Reract from "react";
import {
    Button
} from "@chakra-ui/react"
import { useNavigate } from "react-router-dom";

export default function SignUp() {
    const navigate = useNavigate();
    function newSide(){
        navigate("/home");
    }

    return <div>Welcome to sign up</div>
};