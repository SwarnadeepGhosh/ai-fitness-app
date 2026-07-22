export const authConfig = {
    clientId: import.meta.env.VITE_AUTH_CLIENT_ID,
    authorizationEndpoint: import.meta.env.VITE_AUTH_URL,
    tokenEndpoint: import.meta.env.VITE_AUTH_TOKEN_URL,
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