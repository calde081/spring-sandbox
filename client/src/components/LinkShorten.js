import * as React from 'react';
import Box from '@mui/material/Box';
import TextField from '@mui/material/TextField';
import Button from "@mui/material/Button";
import {useState} from "react";
import isURL from 'validator/lib/isURL'

export default function LinkShorten() {
    const[username, setUsername] = useState('')
    const[link, setLink] = useState('')
    const handleClick = (e)=>{
        e.preventDefault()
        const user = {username, link}
        console.log(user)
        fetch("http://localhost:8080/api/user", {
            method: "POST",
            headers: {"Content-Type":"application/json"},
            body:JSON.stringify(user)
        })
    }

    return (
        <Box
            component="form"
            sx={{
                '& > :not(style)': { m: 1, width: '25ch' },
            }}
            noValidate
            autoComplete="off"
        >
            <TextField id="standard-basic" label="Enter link here" variant="standard" value = {link}
                       error = {!isURL(link)} onChange={(e=>setLink(e.target.value))}/>
            <Button variant="outlined" onClick={handleClick}>Generate</Button>
            <TextField
                disabled
                id="outlined-read-only-input"
                label="Generated Link"
                value = {generated_link}
                defaultValue="Link will be here.."
                InputProps={{
                    readOnly: true,
                }}
            />
        </Box>

    );

}