import React, { Component } from "react";
import { connect } from "react-redux";
import PropTypes from "prop-types";

import ProjectItem from "./Project/ProjectItem";
import CreateProjectButton from "./Project/CreateProjectButton";

import { getProjects } from "../actions/projectActions";

class Dashboard extends Component {
  componentDidMount() {
    const { getProjects } = this.props;
    getProjects();
  }
  render() {
    const { projects } = this.props.project;
    let projectList;

    if (projects) {
      projectList = projects.map(project => (
        <ProjectItem key={project.id} project={project} />
      ));
    } else {
      projectList = <p>Loading...</p>;
    }

    return (
      <div className="projects">
        <div className="container">
          <div className="row">
            <div className="col-md-12">
              <h1 className="display-4 text-center">Projects</h1>
              <br />
              <CreateProjectButton />
              <br />
              <hr />
              {projectList}
            </div>
          </div>
        </div>
      </div>
    );
  }
}

Dashboard.propTypes = {
  getProjects: PropTypes.func.isRequired,
  project: PropTypes.object.isRequired
};

const mapStateToProps = state => ({
  project: state.project
});

export default connect(
  mapStateToProps,
  { getProjects }
)(Dashboard);
