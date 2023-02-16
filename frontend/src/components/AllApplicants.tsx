import Reract, {useEffect, useState} from "react";
import {
    Input,
    Flex,
    Button, Text, Select, Square, VStack
} from "@chakra-ui/react"
import { useNavigate } from "react-router-dom";
import ApiCall from "../apiInterface/ApiCall";
import ApiPost from "../apiInterface/ApiPost";
import ApiPut from "../apiInterface/ApiPut";

export default function AllApplicants() {
    const [errorMessage, setErrorMessage] = useState("")
    const [applicants, setApplicants] = useState("")
    const [applicantsList, setApplicantsList] = useState([])
    const [showAllOrOne, setShowAllOrOne] = useState("all")
    const [theOneToSHow, setTheOneToShow] = useState({
        availabilities: [],
        email: undefined,
        pnr: undefined,
        name: undefined,
        surname: undefined,
        competenceProfiles: [],
        personId: undefined
    })

    const navigate = useNavigate();

    function changeTheOneToShow(response: any) {
        response.then((r: any) => {
            setTheOneToShow(r)
            setShowAllOrOne("one")
        })
    }

    function changeShowingForApp(event: any) {
        fetch('http://localhost:8088/api/application/' + event.target.value).then(response => changeTheOneToShow(response.json()))
    }

    const handleResponse = (response : any) => {
        setApplicants(response)
        setShowAllOrOne("all")
        const appList = response.map((element: any) => {
            if(element.applicationStatus == null) {
                element.applicationStatus = "unhandled"
            }
            return(
            <Flex

                key={element.personId}
                background="blue.200"
                width="fit-content"
                minWidth="100px"
                borderRadius="lg"
                p={3}
                alignSelf="flex-end"
            >
                <Button
                    onClick={changeShowingForApp}
                    value={element.personId}
                    >
                    Go to Applicant
                </Button>
                <Text>
                    {element.name}, {element.surname}:<br />
                     Application status:{element.applicationStatus}<br />
                        Applicant id: {element.personId}
                </Text>
            </Flex>)


        } )

        setApplicantsList(appList)
    };







    function getAllApplicants() {
        ApiCall.getAllApplicants().then(response => handleResponse(response));
        setShowAllOrOne("all")
    }

    function declineApplication() {
        const post = {
            status : "declined",
            id : theOneToSHow.personId
        }
        ApiPut.updateApplicationStatus(post).then(response => console.log(response));
        getAllApplicants();
    }

    function approveApplication() {
        const post = {
            status : "approved",
            id : theOneToSHow.personId
        }
        ApiPut.updateApplicationStatus(post).then(response => console.log(response));
        getAllApplicants();
    }

    return(
    <Flex>


        <Flex color={"red.100"}>
            <form>
                <Text
                    color='red'> {errorMessage} </Text>
                <Button
                    variant="link"
                    width="100%"
                    colorScheme="blue"
                    onClick={getAllApplicants}
                >
                    {" "}
                    Get applicants
                </Button>
            </form>
        </Flex>



        <VStack
            height='calc(90vh)'
            // overflowY="scroll"
            direction="column">
            {showAllOrOne === "all" && applicantsList}
            {showAllOrOne === "one" &&
            <VStack background={"blue.200"} borderRadius={3}>
                <Text>Applicant: {theOneToSHow.name} {theOneToSHow.surname}</Text>
                <Text>PersonalNumber: {theOneToSHow.pnr}</Text>
                <Text> email: {theOneToSHow.email}</Text>
                <Text>Availabilities: </Text>{theOneToSHow.availabilities.map((element:any) => {
                    return(<Flex key = {element.fromDate}>
                           <Text>From  {element.fromDate}  To  {element.toDate}</Text>
                            <br/>
                    </Flex>
                    )}
                 )}
                <Text> Competence: </Text>{theOneToSHow.competenceProfiles.map((element:any) => {
                return(<Flex key = {element.competenceName.name}>
                        <Text>Competence: {element.competenceName.name} for {element.yearsOfExperience} years</Text>
                        <br/>
                    </Flex>
                )}
            )}
                <Button onClick={approveApplication} color={"blue"}>Approve application</Button>
                <Button onClick={declineApplication} color={"red"}>Decline application</Button>
            </VStack>}
        </VStack>
    </Flex>
    )

};