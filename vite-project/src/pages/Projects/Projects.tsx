
import style from './projects.module.css';
import AllProjects from '../../components/AllProjects/AllProjects';

function Projects(): any {  
  return (
    <div className={style.container}> 
      <AllProjects />
    </div>
  );
}

export default Projects;