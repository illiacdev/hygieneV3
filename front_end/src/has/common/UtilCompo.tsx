import {useLocation, useParams} from "react-router-dom";
import { useNavigate } from 'react-router-dom';

export const withRouter = (Component:any) => {
    const Wrapper = (props:any) => {
        const navigate = useNavigate();
        let params = useParams();
        let location = useLocation();

        return (
            <Component
                navigate={navigate}
                params={params}
                location={location}

                {...props}
            />
        );
    };

    return Wrapper;
};
