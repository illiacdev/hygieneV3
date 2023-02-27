import React, {Component} from 'react';
import {withRouter} from "../common/UtilCompo";

class DomainHome extends Component<any,any> {
    render() {
        return (
            <div>
                {JSON.stringify(this.props.params)}
            </div>
        );
    }
}

// withRouter()
export default withRouter(DomainHome);
