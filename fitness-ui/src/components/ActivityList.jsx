import { Card, CardContent, Grid, Typography, IconButton, Box } from '@mui/material'
import React, { useEffect, useState } from 'react'
import { useNavigate } from 'react-router';
import DeleteIcon from '@mui/icons-material/Delete';
import { getActivities, deleteActivity } from '../services/api';

const ActivityList = () => {
  const [activities, setActivities] = useState([]);
  const navigate = useNavigate();

  const fetchActivities = async () => {
    try {
      const response = await getActivities();
      setActivities(response.data);
    } catch (error) {
      console.error(error);
    }
  };

  const handleDelete = async (e, activityId) => {
    // Prevents navigation when clicking the delete button
    e.stopPropagation();
    try {
      await deleteActivity(activityId);

      // Remove the deleted activity from the state to update the UI
      setActivities(activities.filter((activity) => activity.id !== activityId));
    } catch (error) {
      console.error('Error deleting activity:', error);
    }
  };

  useEffect(() => {
    fetchActivities();
  }, []);
  return (
    <Grid container spacing={2}>
      {activities.map((activity) => (
        <Grid container spacing={{ xs: 2, md: 3 }} columns={{ xs: 4, sm: 8, md: 12 }}>
          <Card sx={{ cursor: 'pointer', position: 'relative' }}
            onClick={() => navigate(`/activities/${activity.id}`)}>
            <CardContent>
              <Box sx={{ display: 'flex', justifyContent: 'space-between', alignItems: 'flex-start' }}>
                <Box>
                  <Typography variant='h6'>{activity.type}</Typography>
                  <Typography>Duration: {activity.duration}</Typography>
                  <Typography>Calories: {activity.caloriesBurned}</Typography>
                </Box>
                <IconButton
                  size="small"
                  aria-label="delete"
                  onClick={(e) => handleDelete(e, activity.id)}
                  sx={{
                    color: 'error.main',
                    '&:hover': {
                      backgroundColor: 'error.light', 
                      opacity: 0.8,
                    },
                  }}
                >
                  <DeleteIcon />
                </IconButton>
              </Box>
            </CardContent>
          </Card>
        </Grid>
      ))}
    </Grid>
  )
}

export default ActivityList