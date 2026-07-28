import React, { useEffect, useState } from 'react'
import { useParams, useNavigate, useLocation } from 'react-router'
import { getActivityDetail } from '../services/api';
import { Box, Card, CardContent, Divider, Typography, Button, Container } from '@mui/material';
import ArrowBackIcon from '@mui/icons-material/ArrowBack';
import { GRADIENT, CARD_SHADOW } from '../constants/styles';
import { formatDate, formatDetailDate } from '../utils/formatters';

const renderSection = (title, items) => items.length ? (
  <Box sx={{ mb: 3 }}>
    <Typography variant="h6" sx={{ fontWeight: 'bold', color: '#333', mb: 1 }}>{title}</Typography>
    {items.map((item, idx) => <Typography key={idx} sx={{ mb: 1, ml: 1, color: '#666' }}>• {item}</Typography>)}
  </Box>
) : null;

const ActivityDetail = () => {
  const { id } = useParams();
  const navigate = useNavigate();
  const location = useLocation();
  const [activity, setActivity] = useState(location.state?.activity || null);
  const [recommendation, setRecommendation] = useState(null);

  useEffect(() => {
    getActivityDetail(id).then(res => setRecommendation(res.data)).catch(err => console.error(err));
    window.scrollTo(0, 0);
  }, [id]);

  if (!activity) return <Typography sx={{ p: 3 }}>Loading...</Typography>;

  return (
    <Box sx={{ backgroundColor: '#f5f7fa', minHeight: '100vh', py: 4 }}>
      <Container maxWidth="md">
        <Button variant="outlined" startIcon={<ArrowBackIcon />} onClick={() => navigate('/activities')} sx={{ mb: 3, color: '#667eea', fontWeight: 'bold', textTransform: 'none' }}>
          Back to Activities
        </Button>

        <Card sx={{ mb: 3, boxShadow: CARD_SHADOW }}>
          <Box sx={{ background: GRADIENT, color: 'white', p: 3 }}>
            <Typography variant="h5" sx={{ fontWeight: 'bold' }} >{activity.type}</Typography>
            <Typography variant="body2" sx={{ opacity: 0.9, mt: 1 }} >{formatDetailDate(activity.createdAt)}</Typography>
          </Box>

          <CardContent>
            <Box sx={{ display: 'grid', gridTemplateColumns: '1fr 1fr', gap: 2 }}>
              <Box>
                <Typography variant="body2" sx={{ color: '#999' }} >Duration</Typography>
                <Typography variant="h6" sx={{ fontWeight: 'bold', color: '#333' }} >⏱️ {activity.duration} min</Typography>
              </Box>
              <Box>
                <Typography variant="body2" sx={{ color: '#999' }} >Calories Burned</Typography>
                <Typography variant="h6" sx={{ fontWeight: 'bold', color: '#333' }} >🔥 {activity.caloriesBurned}</Typography>
              </Box>
            </Box>
          </CardContent>
        </Card>

        {recommendation && (
          <Card sx={{ boxShadow: CARD_SHADOW }}>
            <Box sx={{ background: GRADIENT, color: 'white', p: 2.5 }}>
              <Typography variant="h6" sx={{ fontWeight: 'bold' }} >🔥 AI Recommendation</Typography>
            </Box>
            <CardContent>
              {recommendation.recommendation && (
                <Box sx={{ mb: 3 }}>
                  <Typography variant="h6" sx={{ fontWeight: 'bold', color: '#333', mb: 1 }}>Analysis</Typography>
                  <Typography sx={{ color: '#666', mb: 1 }}>{recommendation.recommendation}</Typography>
                </Box>
              )}

              {renderSection('Improvements', recommendation.improvements)}
              {recommendation.suggestions?.length > 0 && <Divider sx={{ my: 2 }} />}
              {renderSection('Suggestions', recommendation.suggestions)}
              {recommendation.safety?.length > 0 && <Divider sx={{ my: 2 }} />}
              {renderSection('Safety Guidelines', recommendation.safety)}
            </CardContent>
          </Card>
        )}
      </Container>
    </Box>
  )
}

export default ActivityDetail