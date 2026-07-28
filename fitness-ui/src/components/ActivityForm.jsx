import { Box, Button, FormControl, InputLabel, MenuItem, Select, TextField, Typography } from '@mui/material'
import React, { useState } from 'react'
import { addActivity } from '../services/api'
import { GRADIENT } from '../constants/styles'

const ActivityForm = ({ onActivityAdded }) => {
    const [activity, setActivity] = useState({
        type: "RUNNING", duration: '', caloriesBurned: ''
    });

    const handleChange = (field, value) => setActivity({ ...activity, [field]: value });

    const handleSubmit = async (e) => {
        e.preventDefault();
        try {
            await addActivity(activity);
            onActivityAdded();
            setActivity({ type: "RUNNING", duration: '', caloriesBurned: '' });
        } catch (error) {
            console.error(error);
        }
    }

    return (
        <Box component="form" onSubmit={handleSubmit} sx={{ mb: 4, p: 3, backgroundColor: 'white', borderRadius: 2, boxShadow: '0 2px 8px rgba(0,0,0,0.1)' }}>
            <Typography variant="h6" sx={{ mb: 3, fontWeight: 'bold', color: '#333' }} >Log Your Activity</Typography>
            <FormControl fullWidth sx={{ mb: 2 }}>
                <InputLabel>Activity Type</InputLabel>
                <Select value={activity.type} onChange={(e) => handleChange('type', e.target.value)}>
                    <MenuItem value="RUNNING">Running</MenuItem>
                    <MenuItem value="WALKING">Walking</MenuItem>
                    <MenuItem value="CYCLING">Cycling</MenuItem>
                </Select>
            </FormControl>
            <TextField fullWidth
                label="Duration (Minutes)"
                type='number'
                sx={{ mb: 3 }}
                value={activity.duration}
                onChange={(e) => handleChange('duration', e.target.value)} />

            <TextField fullWidth
                label="Calories Burned"
                type='number'
                sx={{ mb: 3 }}
                value={activity.caloriesBurned}
                onChange={(e) => handleChange('caloriesBurned', e.target.value)} />

            <Button type='submit' variant='contained' fullWidth sx={{ background: GRADIENT, py: 1.5, fontSize: '1rem', fontWeiht: 'bold' }}>
                + Add Activity
            </Button>
        </Box>
    )
}

export default ActivityForm