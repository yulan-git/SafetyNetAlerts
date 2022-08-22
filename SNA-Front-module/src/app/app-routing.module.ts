import { NgModule } from '@angular/core';
import { PreloadAllModules, RouterModule, Routes } from '@angular/router';
import { AuthGuard } from './auth/auth.guard';

const routes: Routes = [
  { path: '', redirectTo: 'sign-in', pathMatch: 'full' },
  { path: 'persons', canActivate: [AuthGuard], loadChildren: () => import ('./persons/persons.module').then(m => m.PersonsModule) },
  { path: 'firestations', canActivate: [AuthGuard], loadChildren: () => import ('./firestations/firestations.module').then(m => m.FirestationsModule) },
  { path: 'addresses', loadChildren: () => import('./addresses/addresses.module').then(m => m.AddressesModule) },
  { path: '**', loadChildren: () => import('./page-not-found/page-not-found.module').then(m => m.PageNotFoundModule) }
];

@NgModule({
  imports: [RouterModule.forRoot(routes, { preloadingStrategy: PreloadAllModules})],
  exports: [RouterModule]
})
export class AppRoutingModule { }
