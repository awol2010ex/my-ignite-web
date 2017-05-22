var path = require('path')
var webpack = require('webpack')

module.exports = {
    entry: {
	   'main':['./web-console/src/main/vue/main.js']
    },
    output: {
        path: path.resolve(__dirname, './web-console/src/main/webapp/static/js/dist'),
        publicPath: './static/js/dist/',
        filename:  '[name].bundle.js'
    },
	plugins: [
	/*
    new webpack.optimize.UglifyJsPlugin({
      compressor: {
        warnings: false
      }
    }),
	*/
	 new webpack.LoaderOptionsPlugin({
      // test: /\.xxx$/, // may apply this only for some modules
       options: {
           babel: {
               presets: ['es2015'],
               plugins: ['transform-runtime']
          }
       }
     })
    ],
    module: {
        loaders: [
            {
                test: /\.vue$/,
                loader: 'vue-loader'
            },
            {
                test: /\.js$/,
                loader: 'babel-loader',
                exclude: /node_modules/
            },
            {
                test: /\.css$/,
                use: ['style-loader', 'css-loader']
            },
            {
                test: /\.(json|eot|svg|ttf|woff|woff2)(\?\S*)?$/,
                loader: 'file-loader'
            },
            {
                test: /\.(png|jpg|gif|svg)$/,
                loader: 'file-loader',
                query: {
                    name: '[name].[ext]?[hash]'
                }
            }
        ]
    },
	
    resolve: {
        alias: {
                    vue: 'vue/dist/vue.js'
        }
    }
}
