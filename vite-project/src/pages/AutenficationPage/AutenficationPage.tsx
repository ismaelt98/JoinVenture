/*import Login from "../../components/Login/Login";
import Test from "../../components/Test/Test";
import style from './autenfication.module.css'*/

function AutenficationPage() {
    const [currentBackground, setCurrentBackground] = useState(0);
    const backgrounds = [background1, background2, background3];
    useEffect(() => {
        const interval = setInterval(() => {
            setCurrentBackground((prev) => (prev === 2 ? 0 : prev + 1));
        }, 3000);

        return () => clearInterval(interval);
    }, []);

    return (
        <div className="">
            <h1>USTED SE LOGUE CORRECTAMENTE</h1>
        </div>
    );
}

export default AutenficationPage;