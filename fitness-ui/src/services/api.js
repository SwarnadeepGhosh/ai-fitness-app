import axios from "axios";

const baseURL = (import.meta.env.VITE_API_BASE_URL || 'https://sg-devops.centralindia.cloudapp.azure.com/api')
    .replace(/^http:\/\//, 'https://');

const api = axios.create({
    baseURL,
});

api.interceptors.request.use((config) => {
    const token = localStorage.getItem('token');
    const userId = localStorage.getItem('userId');
    if (token) config.headers['Authorization'] = `Bearer ${token}`;
    if (userId) config.headers['X-User-ID'] = userId;
    return config;
});

export const getActivities = () => api.get('/activities');
export const addActivity = (activity) => api.post('/activities/save', activity);
export const getActivityDetail = (id) => api.get(`/recommendations/activity/${id}`);
export const deleteActivity = (id) => api.delete(`/activities/${id}`);