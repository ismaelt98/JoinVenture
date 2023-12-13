/*import Login from "../../components/Login/Login";
import Test from "../../components/Test/Test";*/
import { useEffect, useState } from 'react';
import style from './adminusers.module.css'

function AdminUsers(): any {

    interface User {
        id: number;
        username: string;
        alias:string;
        email: string;
        phone: string;
        roleuser: string;
    }

    const [users, setUsers] = useState<User[]>([]);
    
    useEffect(() => {
        const fetchData = async () => {
            try {
                const response = await fetch("http://localhost:8080/users");
                const data: User[] = await response.json();
                setUsers(data);
            } catch (error) {
                console.log('error', error);
            }
        };
        fetchData();
    }, []); // Ejecución al montar el componente



    return (
        <div>
            <table className={style.table}>
                <thead>
                    <tr>
                        <th>Id Usuario</th>
                        <th>Nombre Usuario</th>
                        <th>Alias</th>
                        <th>Email</th>
                        <th>Telefono</th>
                        <th>Role User</th>
                        <th>Opciones</th>
                        
                    </tr>
                </thead>
                <tbody>
                {users.map((user) => (
                        <tr key={user.id}>
                            <td>{user.id}</td>
                            <td>{user.username}</td>
                            <td>{user.alias}</td>
                            <td>{user.email}</td>
                            <td>{user.phone}</td>
                            <td>{user.roleuser}</td>
                            <td><button>DEL</button></td>
                        </tr>
                    ))}
                   
                </tbody>
            </table>
        </div>
    );
}

export default AdminUsers;