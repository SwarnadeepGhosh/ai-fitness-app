export const authConfig = {
    clientId: 'oauth2-pkce-client',
    authorizationEndpoint: 'http://sg-devops.centralindia.cloudapp.azure.com/auth/realms/fitness-app/protocol/openid-connect/auth',
    tokenEndpoint: 'http://sg-devops.centralindia.cloudapp.azure.com/auth/realms/fitness-app/protocol/openid-connect/token',
    redirectUri: 'http://localhost:5173',
    scope: 'openid profile email offline_access',
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