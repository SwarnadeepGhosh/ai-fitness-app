import axios from "axios";

// const API_URL = 'http://localhost:8080/api';

const rawBase = import.meta.env.VITE_API_BASE_URL || 'https://sg-devops.centralindia.cloudapp.azure.com/api';
const baseURL = (typeof rawBase === 'string' && rawBase.startsWith('http://'))
    ? rawBase.replace(/^http:\/\//, 'https://')
    : rawBase;

const api = axios.create({
        baseURL,
});

api.interceptors.request.use((config) => {
    const userId = localStorage.getItem('userId');
    const token = localStorage.getItem('token');

    if (token) {
        config.headers['Authorization'] = `Bearer ${token}`;
    }

    if (userId) {
        config.headers['X-User-ID'] = userId;
    }
    return config;
}
);


export const getActivities = () => api.get('/activities');
export const addActivity = (activity) => api.post('/activities/save', activity);
export const getActivityDetail = (id) => api.get(`/recommendations/activity/${id}`);