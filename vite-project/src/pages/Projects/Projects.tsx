
import { useEffect, useState } from 'react';
import style from './projects.module.css'
import Cookies from 'js-cookie';
import AllProjects from '../../components/AllProjects/AllProjects';
function Projects(): any {
    const [dataRoleUser, setDataRoleUser] = useState(true);
    const roleuser = Cookies.get('roleuser');
    
    useEffect(() => {
        
            if (roleuser === 'PROGRAMADOR') {
              setDataRoleUser(true);
            } else {
              setDataRoleUser(false);
            }
        
      }, [roleuser]);
    
    
    return (
        <div className={style.divGeneral}>
            <div style={{ display: dataRoleUser ? 'flex' : 'none' }} className={style.navbar1}>
                <button className={style.btnProjectsG}>Todos los Proyectos</button>
                <button className={style.btnProjectsG}>Mis Proyectos</button>
                <button className={style.btnProjectsG}>Crear Proyecto</button>
            </div>
            <div>
                <AllProjects/>
            </div>
        </div>
    );
}

export default Projects;