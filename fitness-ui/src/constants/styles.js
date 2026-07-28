export const GRADIENT = "linear-gradient(135deg, #667eea 0%, #764ba2 100%)";
export const PRIMARY_COLOR = "#667eea";
export const CARD_SHADOW = "0 2px 6px rgba(0, 0, 0, 0.1)";
export const DARK_TEXT = '#333';
export const LIGHT_TEXT = '#666';
export const MUTED_TEXT = '#999';

export const gradientBox = {
    background: GRADIENT,
    color: 'white',
    p: 3,
};

export const cardHover = {
    transition: 'all 0.3s, ease',
    '&:hover': {
        transform: 'translateY(-4px)',
        boxShadow: `0 8px 16px rgba(102, 126, 234, 0.15)`,
    },
};

export const hoverButtonHover = {
    '&:hover': {
        backgroundColor: 'rgba(255, 255, 255, 0.1)',
    },
};
