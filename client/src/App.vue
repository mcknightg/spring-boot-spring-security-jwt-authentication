<template>
    <div id="app">
        <nav class="navbar navbar-expand ">
            <router-link to="/"><img width="120px;" class="logo-bar" src="/img/logo-wide.png" alt="Logo"></router-link>
            <div class="navbar-nav mr-auto">


                <li class="nav-item">
                    <router-link v-if="currentUser" to="/user" class="nav-link">User</router-link>
                </li>
            </div>

            <div v-if="!currentUser" class="navbar-nav ml-auto">
                <li class="nav-item">
                    <router-link to="/register" class="nav-link">
                        <font-awesome-icon icon="user-plus"/>
                        Sign Up
                    </router-link>
                </li>
                <li class="nav-item">
                    <router-link to="/login" class="nav-link">
                        <font-awesome-icon icon="sign-in-alt"/>
                        Login
                    </router-link>
                </li>
            </div>

            <div v-if="currentUser" class="navbar-nav ml-auto">
                <li class="nav-item">
                    <router-link to="/profile" class="nav-link">
                        <font-awesome-icon icon="user"/>
                        {{ currentUser.username }}
                    </router-link>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href @click.prevent="logOut">
                        <font-awesome-icon icon="sign-out-alt"/>
                        LogOut
                    </a>
                </li>
            </div>
        </nav>
        <div class="bg-light border-right" id="sidebar-wrapper">

            <div class="list-group list-group-flush">
                <router-link to="/students">Students</router-link>
                <router-link to="/instructors">Instructors</router-link>
                <router-link to="/studenttypes">Student Types</router-link>
            </div>
        </div>
        <div class="container">
            <router-view/>
        </div>
    </div>
</template>

<script>
    export default {
        computed: {
            currentUser() {
                return this.$store.state.auth.user;
            },
            isAdmin() {
                if (this.currentUser && this.currentUser.roles) {
                    return this.currentUser.roles.includes('ROLE_ADMIN');
                }

                return false;
            },
            isAuthenticated() {
                if (this.currentUser()) {
                    return true;
                }
                return false;
            },
            isUser() {
                if (this.currentUser && this.currentUser.roles) {
                    return this.currentUser.roles.includes('ROLE_USER');
                }

                return false;
            }
        },
        methods: {
            logOut() {
                this.$store.dispatch('auth/logout');
                this.$router.push('/login');
            }
        }
    };
</script>
