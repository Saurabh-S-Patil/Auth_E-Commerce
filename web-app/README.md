# ecommerce demo application

## VS Code extensions

- https://marketplace.visualstudio.com/items?itemName=steoates.autoimport
- https://marketplace.visualstudio.com/items?itemName=NuclleaR.vscode-extension-auto-import
- https://marketplace.visualstudio.com/items?itemName=streetsidesoftware.code-spell-checker

## installation

```bash

# create a new react app
> npx create-react-app web-app

> cd web-app

# install required packages
> yarn add react-router-dom react-redux @reduxjs/toolkit axios react-toastify

```

## redux

- create a store

```javascript
import { configureStore } from '@reduxjs/toolkit'

// create a new store
export const store = configureStore({
  reducer: {},
})
```

- include the whole application in Provider Object inside index.js

```javascript
import { Provider } from 'react-redux'
import { store } from './store'

const root = ReactDOM.createRoot(document.getElementById('root'))
root.render(
  <React.StrictMode>
    <Provider store={store}>
      <BrowserRouter>
        <App />
      </BrowserRouter>
    </Provider>
  </React.StrictMode>
)
```

- create a slice

```javascript
import { createSlice } from '@reduxjs/toolkit'

export const authSlice = createSlice({
  // name of slice (must be unique)
  name: 'auth',
  initialState: {
    status: false,
  },
  reducers: {
    // action: action handler
    login: (state) => {
      state.status = true
    },
    // action: action handler
    logout: (state) => {
      state.status = false
    },
  },
})

export const { login, logout } = authSlice.actions
export default authSlice.reducer
```

- add the slice's reducer to the store configuration

```javascript
import { configureStore } from '@reduxjs/toolkit'
import authSlice from './features/authSlice'

// create a new store
export const store = configureStore({
  reducer: {
    auth: authSlice,
  },
})
```
