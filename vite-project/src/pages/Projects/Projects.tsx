
import style from './projects.module.css';
import AllProjects from '../../components/AllProjects/AllProjects';
import Cookies from 'js-cookie';

function Projects(): any {
  const roleUser = Cookies.get('roleuser');
  return (
    <div className={style.container}>

      {roleUser != 'empresa' ? (
        <button className={style.btnCrearProject}>+</button>
      ) : (
        <></>
      )}
      <AllProjects />
    </div>
  );
}

export default Projects;