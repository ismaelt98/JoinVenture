
import { useEffect, useState } from 'react';
import style from './projects.module.css'
import Cookies from 'js-cookie';
import AllProjects from '../../components/AllProjects/AllProjects';
function Projects(): any {
  const [dataRoleUser, setDataRoleUser] = useState(true);
  const roleuser = Cookies.get('roleuser');

  useEffect(() => {

    if (roleuser === 'programador') {
      setDataRoleUser(true);
    } else {
      setDataRoleUser(false);
    }

  }, [roleuser]);


  return (
    <div className={style.container}>
 
      <AllProjects />
    </div>
  );
}

export default Projects;