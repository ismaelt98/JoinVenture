
import style from './projects.module.css';
import AllProjects from '../../components/AllProjects/AllProjects';
import Cookies from 'js-cookie';
import { Link } from 'react-router-dom';

function Projects(): any {
  const roleUser = Cookies.get('roleuser');
  return (
    <div className={style.container}>

      {roleUser != 'empresa' ? (
        
          <div className={style.crearProject}>
            <Link className={style.crearProject1} to="../crearproject">➕</Link>
          </div>
     
      ) : (
        <></>
      )}
      <AllProjects />
    </div>
  );
}

export default Projects;