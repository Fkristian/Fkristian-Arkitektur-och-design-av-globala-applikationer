import Reract, {useState} from "react";
import React, { Component }  from 'react';
import {
    Input,
    Flex,
    Button, Text, Select, VStack
} from "@chakra-ui/react"
import { useNavigate } from "react-router-dom";
import ApiCall from "../apiInterface/ApiCall";
import ApiPost from "../apiInterface/ApiPost";

export default function ApplicantForm() {
    const [errorMessage, setErrorMessage] = useState("")
    const [competenceArray, setCompetenceArray] = useState([])
    const [availabilityArray, setAvailabilityArray] = useState([])
    const [compFormData, setCompFormData] = useState({
        competence: "",
        yearsOfExperience: "",
    })
    const [avFormData, setAvFormData] = useState({
        startDate: "",
        endDate: "",
    })
    const navigate = useNavigate();

    function handleCompChange(event: { target: { name: any; value: any; }; }) {
        const { name, value } = event.target;
        setCompFormData((prevValues) => {
            return {
                ...prevValues,
                [name]: value,
            };
        });
    }
    function handleAvChange(event: { target: { name: any; value: any; }; }) {
        const { name, value } = event.target;
        setAvFormData((prevValues) => {
            return {
                ...prevValues,
                [name]: value,
            };
        });
    }

    function addCompetence() {

        if(compFormData.competence === "" || compFormData.yearsOfExperience === ""){
            setErrorMessage("Fill in all fields")
        }else{

            const post:any = {
                competence :  compFormData.competence,
                yearsOfExperience : compFormData.yearsOfExperience

            }
            setCompetenceArray(competenceArray.concat(post))
        }
    }

    function addAvailability() {
        if(avFormData.startDate === "" || avFormData.endDate === ""){
            setErrorMessage("Fill in all fields")
        }else{
            const post:any = {
                startDate :  avFormData.startDate,
                endDate : avFormData.endDate

            }
            setAvailabilityArray(availabilityArray.concat(post))
        }
    }
    function test1(){
        console.log(competenceArray)
        console.log(availabilityArray)

        ApiPost.createApplication(competenceArray).then(response => {
            if(typeof response === "string"){
                setErrorMessage(response)
            }else{
                handleResponse(response)
            }
        });
        ApiPost.createApplication(availabilityArray).then(response => {
            if(typeof response === "string"){
                setErrorMessage(response)
            }else{
                handleResponse(response)
            }
        });
    }

    const handleResponse = (response : Response) => {
        if (response.ok) {
            console.log("ok");
        }else{
            console.log("error" + response.status)
        }
    };

    /*
    function showCompetences() {
        if (competenceArray.length > 0) {
                const val = competenceArray.map(item => {
                return <text>item</text>
            })
        }
        else return null
    };

     */

    return(
        <VStack>

            <form>
                <Text color='red'> {errorMessage} </Text>
                <h3>Add Competences</h3>
                <Select placeholder='Select option' onChange={handleCompChange} name="competence">
                    <option defaultValue={compFormData.competence} label="Ticket Sales">ticket sales</option>
                    <option defaultValue={compFormData.competence} label="Lotteries">lotteries</option>
                    <option defaultValue={compFormData.competence} label="Roller Coaster Operation">roller coaster operation</option>
                </Select>
                <Input
                    type="text"
                    placeholder="Years of Experience"
                    onChange={handleCompChange}
                    name="yearsOfExperience"
                    mb={3}
                    defaultValue={compFormData.yearsOfExperience}
                />
                <Button
                    width="100%"
                    colorScheme="blue"
                    onClick={addCompetence}
                    mb={3}
                >
                    Add Competence
                </Button>
            </form>

            <form>
                <Text color='red'> {errorMessage} </Text>
                <h3>Add Availability Periods</h3>
                <Input
                    width="50%"
                    type="text"
                    placeholder="Start Date yyyy-mm-dd"
                    onChange={handleAvChange}
                    name="startDate"
                    mb={3}
                    defaultValue={avFormData.startDate}
                />
                <Input
                    width="50%"
                    type="text"
                    placeholder="End Date yyyy-mm-dd"
                    onChange={handleAvChange}
                    name="endDate"
                    mb={3}
                    defaultValue={avFormData.endDate}
                />
                <Button
                    width="50%"
                    colorScheme="blue"
                    onClick={addAvailability}
                    mb={3}
                >
                    Add Availability Period
                </Button>
            </form>
            <Button
                variant="link"
                width="100%"
                colorScheme="blue"
                onClick={test1}
            >
                Hand in Application
            </Button>

        </VStack>
    )

};