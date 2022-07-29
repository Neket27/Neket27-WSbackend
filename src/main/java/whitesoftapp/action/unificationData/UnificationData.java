package whitesoftapp.action.unificationData;

import whitesoftapp.arguments.CreateEmployeeArgument;
import whitesoftapp.arguments.UpdateEmployeeArgument;
import whitesoftapp.model.Employee;

public interface UnificationData {

    Employee unificationDataEmployeeWitchPost(CreateEmployeeArgument createEmployeeArgument);

    Employee unificationDataEmployeeWitchPost(UpdateEmployeeArgument updateEmployeeArgument);
}

