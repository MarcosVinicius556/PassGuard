app.controller('userController', ($scope, UserService) => {
    $scope.users = [];

    $scope.user = {
        username: '',
        nickname: '',
        password: ''
    }


    $scope.createUser = () => {
        let userData = $scope.user;

        if(userData.username === '') {
            alert('É necessário informar o nome de usuário!')
            return;
        }

        if(userData.nickname === '') {
            alert('É necessário informar um nickname!')
            return;
        }

        if(userData.password === '') {
            alert('É necessário informar uma senha!')
            return;
        }

        UserService.create(userData);

    };

    $scope.findAll = () => {
        UserService.findAll().then((response) => {
            $scope.users = response.data;
        });
    }
});