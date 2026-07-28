import { Card, CardContent, Typography, IconButton, Box, Icon } from '@mui/material'
import React, { useEffect, useState } from 'react'
import { useNavigate } from 'react-router';
import DeleteIcon from '@mui/icons-material/Delete';
import { getActivities, deleteActivity } from '../services/api';
import { CARD_SHADOW, PRIMARY_COLOR, LIGHT_TEXT, MUTED_TEXT, cardHover } from '../constants/styles';
import { formatDate } from '../utils/formatters';

const ActivityList = () => {
  const [activities, setActivities] = useState([]);
  const navigate = useNavigate();

  const fetchActivities = async () => {
    try {
      const response = await getActivities();
      setActivities([...response.data].sort((a, b) => new Date(b.createdAt) - new Date(a.createdAt))); // Sort by date descending
    } catch (error) {
      console.error(error);
    }
  };

  useEffect(() => {
    fetchActivities();
  }, []);

  if (!activities.length) {
    return <Box sx={{ textAlign: 'center', py: 4 }}>
      <Typography variant="h6" sx={{ color: MUTED_TEXT }}>No activities yet. Start by adding one!</Typography>
    </Box>
  };

  return (
    <Box sx={{ display: 'grid', gridTemplateColumns: { xs: '1fr', sm: '1fr 1fr', md: '1fr 1fr 1fr' }, gap: 3 }}>
      {activities.map(({ id, type, createdAt, duration, caloriesBurned }) => (
        <Card key={id} sx={{ cursor: 'pointer', position: 'relative', height: '100%', backgroundColor: 'white', border: '1px solid #f0f0f0', ...cardHover }}
          onClick={() => navigate(`/activities/${id}`, { state: { activity: { id, type, createdAt, duration, caloriesBurned } } })}>
          <CardContent sx={{ pb: 2 }}>
            <Box sx={{ display: 'flex', justifyContent: 'space-between', alignItems: 'flex-start' }}>
              <Box sx={{ flex: 1 }}>
                <Typography variant='h6' sx={{ color: PRIMARY_COLOR, fontWeight: 'bold', mb: 1 }}>{type}</Typography>
                <Typography variant='caption' sx={{ color: MUTED_TEXT, display: 'block', mb: 1 }}>📅 {formatDate(createdAt)}</Typography>
                <Typography variant='body2' sx={{ color: LIGHT_TEXT, mb: 0.5 }}>⏱️ Duration: <strong>{duration}</strong></Typography>
                <Typography variant='body2' sx={{ color: LIGHT_TEXT }}>🔥 Calories: <strong>{caloriesBurned}</strong></Typography>
              </Box>

              <IconButton size="small" onClick={(e) => {
                e.stopPropagation();
                deleteActivity(id).then(() => setActivities(activities.filter((activity) => activity.id !== id))).catch(err => console.error(err));
              }} sx={{ color: '#ff6b6b', backgroundColor: 'rgba(255, 107, 107, 0.1)', '&:hover': { backgroundColor: '#ff6b6b', color: 'white' } }}>
                <DeleteIcon fontSize='small' />
              </IconButton>
            </Box>
          </CardContent>
        </Card>
      ))
      }
    </Box >
  )
}

export default ActivityList