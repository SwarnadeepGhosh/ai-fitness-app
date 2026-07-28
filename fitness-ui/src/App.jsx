import { Box, Button, Typography, AppBar, Toolbar, Container } from "@mui/material";
import FitnessCenterIcon from '@mui/icons-material/FitnessCenter';
import LogoutIcon from '@mui/icons-material/Logout';
import { useContext, useEffect } from "react";
import { AuthContext } from "react-oauth2-code-pkce";
import { useDispatch } from "react-redux";
import { BrowserRouter as Router, Navigate, Route, Routes } from "react-router";
import { setCredentials } from "./store/authSlice";
import { GRADIENT } from "./constants/styles";
import ActivityForm from "./components/ActivityForm";
import ActivityList from "./components/ActivityList";
import ActivityDetail from "./components/ActivityDetail";

const ActvitiesPage = () => (
  <Box sx={{ py: 4 }}>
    <ActivityForm onActivityAdded={() => window.location.reload()} />
    <ActivityList />
  </Box>
);

function App() {
  const { token, tokenData, logIn, logOut } = useContext(AuthContext);
  const dispatch = useDispatch();

  useEffect(() => {
    if (token) dispatch(setCredentials({ token, user: tokenData }));
  }, [token, tokenData, dispatch]);

  return (
    <Router>
      {!token ? (
        <Box
          sx={{
            height: "100vh",
            display: "flex",
            flexDirection: "column",
            alignItems: "center",
            justifyContent: "center",
            textAlign: "center",
            background: GRADIENT,
          }}
        >
          <FitnessCenterIcon sx={{ fontSize: 80, color: "white", mb: 3 }} />
          <Typography variant="h3" gutterBottom sx={{ color: "white", fontWeight: 'bold' }}>
            Fitness Tracker
          </Typography>
          <Typography variant="h6" sx={{ mb: 4, color: 'rgba(255, 255, 255, 0.9)' }}>
            Track your workouts and achieve your fitness goals with ease. Log in to get started!
          </Typography>
          <Button size="large" onClick={() => logIn()} sx={{
            px: 5, py: 1.5, fontSize: '1.1rem', backgroundColor: 'white', color: '#667eea', '&:hover': { backgroundColor: '#f0f0f0' }
          }}>
            LOGIN
          </Button>
        </Box>
      ) : (
        <Box sx={{ display: 'flex', flexDirection: 'column', minHeight: '100vh', backgroundColor: '#f5f7fa' }}>
          <AppBar position="sticky" sx={{ background: GRADIENT, boxShadow: '0 2px 8px rgba(0,0,0,0.1)' }}>
            <Toolbar>
              <FitnessCenterIcon sx={{ mr: 2 }} />
              <Typography variant="h6" sx={{ flexGrow: 1, fontWeight: 'bold' }}>
                Fitness Tracker
              </Typography>
              <Button color="inherit" onClick={logOut} startIcon={<LogoutIcon />} sx={{ '&:hover': { backgroundColor: 'rgba(255, 255, 255, 0.1)' } }}>
                Logout
              </Button>
            </Toolbar>
          </AppBar>
          <Container maxWidth="lg" sx={{ flex: 1, py: 3 }}>
            <Routes>
              <Route path="/activities" element={<ActvitiesPage />} />
              <Route path="/activities/:id" element={<ActivityDetail />} />

              <Route path="/" element={token ? <Navigate to="/activities" replace /> : <div>Welcome! Please Login.</div>} />
            </Routes>
          </Container>
        </Box>
      )}
    </Router>
  );
}

export default App
