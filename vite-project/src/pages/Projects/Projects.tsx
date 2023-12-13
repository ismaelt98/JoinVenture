
import style from './projects.module.css';
import AllProjects from '../../components/AllProjects/AllProjects';

function Projects(): any {  
  return (
    <div className={style.container}>
      <button className={style.btnCrearProject}>+</button> 
      <AllProjects/>
    </div>
  );
}

export default Projects;