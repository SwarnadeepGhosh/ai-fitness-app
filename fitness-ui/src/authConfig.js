const ensureHttps = (val, fallback = '') => {
  const raw = val || fallback || '';
  return (typeof raw === 'string' && raw.startsWith('http://')) ? raw.replace(/^http:\/\//, 'https://') : raw;
}

export const authConfig = {
    clientId: import.meta.env.VITE_AUTH_CLIENT_ID,
    authorizationEndpoint: ensureHttps(import.meta.env.VITE_AUTH_URL, 'https://sg-devops.centralindia.cloudapp.azure.com/auth/realms/fitness-app/protocol/openid-connect/auth'),
    tokenEndpoint: ensureHttps(import.meta.env.VITE_AUTH_TOKEN_URL, 'https://sg-devops.centralindia.cloudapp.azure.com/auth/realms/fitness-app/protocol/openid-connect/token'),
    redirectUri: import.meta.env.VITE_AUTH_REDIRECT_URI,
    scope: import.meta.env.VITE_AUTH_SCOPE,
    autoLogin: false,
    clearURL: true,
    onRefreshTokenExpire: (event) => event.logIn(),
  }

// Clean up stale PKCE state from interrupted auth flows
// This prevents the "Bad authorization state" error when the auth server was unreachable
const url = new URL(window.location.href);
if (!url.searchParams.has('code')) {
  sessionStorage.removeItem('PKCE_code_verifier');
  sessionStorage.removeItem('ROCP_loginInProgress');
}