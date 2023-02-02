import Reract from "react";
import {
    Button
} from "@chakra-ui/react"
import { useNavigate } from "react-router-dom";

export default function LogIn() {
    const navigate = useNavigate();
    function newSide(){
        navigate("/home");
    }

    return <div>Welcome to home</div>
};