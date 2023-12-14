import React, { useState, useEffect } from 'react';
import axios from 'axios';
import Cookies from 'js-cookie';
import style from './perfil.module.css';
import imagen from '../../assets/user.png'
interface ProfileData {
    username: string;
    alias: string;
    email: string;
    password: string;
    phone: string;
    roleuser: string;
}

const Perfil: any = () => {
    const [profileData, setProfileData] = useState<ProfileData | null>(null);
    const [editedData, setEditedData] = useState<Partial<ProfileData>>({});
    const [isEditing, setIsEditing] = useState(false);
    const idUser = Cookies.get('id');
    useEffect(() => {
        // Función para obtener los datos del perfil desde la API al cargar el componente
        const fetchProfileData = async () => {
            try {
                const response = await axios.get(`http://localhost:8080/users/${idUser}`); // Ruta de la API para obtener datos del perfil
                setProfileData(response.data);
            } catch (error) {
                // Manejo de errores
            }
        };
        fetchProfileData();
    }, []);

    const handleEditClick = () => {
        setIsEditing(true);
        setEditedData({ ...profileData });
    };

    const handleInputChange = (e: React.ChangeEvent<HTMLInputElement>) => {
        const { name, value } = e.target;
        setEditedData((prevData) => ({ ...prevData, [name]: value }));
    };

    const handleSaveClick = async () => {
        try {
            if (editedData && Object.keys(editedData).length > 0) {
                const updatedProfileData = { ...profileData, ...editedData } as ProfileData;
                await axios.patch(`http://localhost:8080/users/${idUser}`, updatedProfileData);
                setIsEditing(false);
                setProfileData(updatedProfileData);
            }
        } catch (error) {
            // Manejo de errores al guardar cambios
        }
    };

    return (
        <div>
            {profileData ? (
                <div>

                    {!isEditing ? (
                        <div className={style.profileContainer}>
                            <div className={style.profileHeader}>
                                <img src={imagen} alt="Foto de perfil" className={style.profilePicture} />
                                <h2>{profileData.username}</h2>
                            </div>
                            <div className={style.profileInfo}>
                                <p><strong>Email: </strong>{profileData.email}</p>
                                <p><strong>Alias: </strong>{profileData.alias}</p>
                                <p><strong>Telefono: </strong>{profileData.phone}</p>
                                <button onClick={handleEditClick}>Editar Perfil</button>
                            </div>
                        </div>
                    ) : (
                        <>
                            <div className={style.profileContainer}>
                                <div className={style.profileHeader}>
                                    <img src={imagen} alt="Foto de perfil" className={style.profilePicture} />
                                    <input
                                        className={style.inputName}
                                        type="text"
                                        name="username"
                                        value={editedData.username || ''}
                                        onChange={handleInputChange}
                                    />
                                </div>
                                <div className={style.profileInfo}>
                                    <input
                                        type="text"
                                        name="email"
                                        disabled
                                        value={editedData.email || ''}
                                        onChange={handleInputChange}
                                    />
                                    <input
                                        type="text"
                                        name="alias"
                                        required
                                        value={editedData.alias || ''}
                                        onChange={handleInputChange}
                                    />

                                    <input
                                        type="number"
                                        name="phone"
                                        value={editedData.phone || ''}
                                        onChange={handleInputChange}
                                        required
                                    />
                                    <button onClick={handleSaveClick}>Guardar Cambios</button>
                                </div>
                            </div>


                        </>




                    )}
                </div>
            ) : (
                <p>Cargando perfil...</p>
            )}
        </div>
    );
};

export default Perfil;