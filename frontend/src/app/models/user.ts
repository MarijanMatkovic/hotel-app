import {Role} from './role'

export class User {
  constructor(username: string, role: string) {
    this.username = username
    switch(role){
      case 'ROLE_OWNER':
        this.role = Role.Owner
        break
      case 'ROLE_ADMIN':
        this.role = Role.Admin
        break
      case 'ROLE_HOUSEKEEPER':
        this.role = Role.Housekeeper
        break
      case 'ROLE_RECEPTIONIST':
        this.role = Role.Receptionist
        break
      default:
        this.role = Role.Guest
    }
  }

  username: string;
  role: Role;
}
