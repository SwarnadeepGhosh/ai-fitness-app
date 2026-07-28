export const formatDate = (dateString, options = {}) => {
    const defaultOptions = { month: 'short', day: 'numeric', year: 'numeric', hour: '2-digit', minute: '2-digit' };
    return new Date(dateString).toLocaleDateString('en-US', { ...defaultOptions, ...options });
};

export const formatDetailDate = (dateString) => {
    return new Date(dateString).toLocaleDateString('en-US', { weekday: 'long', year: 'numeric', month: 'long', day: 'numeric' });
};