import React, { ReactNode } from 'react';
import { Navigate } from 'react-router-dom';
import Cookies from 'js-cookie';

interface AuthGuardProps {
    children: ReactNode;
}
const AuthGuard: React.FC<AuthGuardProps> = ({ children }) => {
    const isAuthenticated = Cookies.get('id') !== undefined;

    if (!isAuthenticated) {
        alert('Debes iniciar sesión para acceder a esta página.');
        return <Navigate to="/login" />;
    }

    return children;
};

export default AuthGuard;