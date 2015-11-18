/**
 * Copyright 2015, deepsense.io
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package io.deepsense.workflowexecutor.communication

import spray.json.{DefaultJsonProtocol, RootJsonFormat}

import io.deepsense.commons.utils.Logging
import io.deepsense.graph.{DirectedGraph, Node}
import io.deepsense.models.json.workflow.WorkflowJsonProtocol
import io.deepsense.models.workflows.Workflow

case class Launch(
    workflowId: Workflow.Id,
    workflow: DirectedGraph,
    nodesToExecute: Seq[Node.Id])
  extends ReadMessageMQ

object Launch {
  val messageType: String = "launch"
}

trait LaunchJsonProtocol
    extends DefaultJsonProtocol
    with Logging {
  self: WorkflowJsonProtocol =>

  implicit val launchFormat: RootJsonFormat[Launch] = jsonFormat3(Launch.apply)
}
