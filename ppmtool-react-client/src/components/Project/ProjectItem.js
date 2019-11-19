import React, { Component } from "react";
import { NavLink } from "react-router-dom";

class ProjectItem extends Component {
  render() {
    const {
      projectName,
      projectIdentifier,
      description,
      id
    } = this.props.project;
    return (
      <div className="container">
        <div className="card card-body bg-light mb-3">
          <div className="row">
            <div className="col-lg-2 col-md-2 col-sm-3">
              <span className="mx-auto">{projectIdentifier}</span>
            </div>
            <div className="col-lg-6 col-md-4 col-sm-8">
              <h3>{projectName}</h3>
              <p>{description}</p>
            </div>
            <div className="col-lg-4 col-md-4 col-sm-12">
              <ul className="list-group">
                <NavLink className="project-action-links" to={`#!`}>
                  <li className="list-group-item board">
                    <i className="fa fa-flag-checkered mr-1" />
                    <span>Project Board</span>
                  </li>
                </NavLink>
                <NavLink
                  className="project-action-links"
                  to={`updateProject/${id}`}
                >
                  <li className="list-group-item update">
                    <i className="fa fa-edit mr-1" />
                    <span>Update Project Info</span>
                  </li>
                </NavLink>
                <NavLink className="project-action-links" to={`#!`}>
                  <li className="list-group-item delete">
                    <i className="fa fa-minus-circle mr-1" />
                    <span>Delete Project</span>
                  </li>
                </NavLink>
              </ul>
            </div>
          </div>
        </div>
      </div>
    );
  }
}

export default ProjectItem;
